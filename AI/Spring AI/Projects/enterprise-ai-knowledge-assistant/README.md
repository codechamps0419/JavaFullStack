# DocPilot — Enterprise AI Knowledge Assistant

A Spring Boot / Spring AI + PostgreSQL pgvector PDF RAG application with access-controlled 
and source-page citations.

## Versions and environment

- Java 17+, Maven 3.9+, Docker Desktop, IntelliJ IDEA or VS Code.
- Spring Boot 3.5.7; Spring AI 1.1.8; PostgreSQL 16 with pgvector.
- OpenAI `gpt-4o-mini` and `text-embedding-3-small` (1536 dimensions). Live provider calls cost money; an API key and internet access are needed.
- Built for local education/demonstration; **not an independently audited or production-hardened system**.

## Steps — start to finish

1. Install JDK 17+, Maven and PostgreSQL. In IntelliJ open this folder as a Maven project. 
   Confirm `java -version`, `mvn -version` in terminal.
2. Start SQL Shell (psql) and run SQL queries present in V1_init.sql file.
3. Create an API key at your AI provider. Add configurations mentioned in config-setup folder.
4. Check `http://localhost:8080/actuator/health`, then open **http://localhost:8080/**. 
   Sign in with `aprajita / aprajita123!` for the demo UI, upload PDF, ask question and delete PDF Document.
5. Convert `sample-docs/employee-policy.txt` to a text-based document using Word > Save As PDF. Upload it with visibility `PUBLIC`. 
   Ask: **How much annual leave do employees receive?** The UI shows an answer and page citations.
6. For API testing import `postman/DocPilot.postman_collection.json` into Postman. 
   Under Collection Authorization choose Basic Auth. 
   To test Rekha, change username/password to `rekha / rekha123!`.

**Important:** Do not store API KEY on GitHub. The provided demo passwords are intentionally public,
change them before any shared deployment. DO NOT expose the app or database directly to the internet.

## Postman walkthrough and example outputs

**GET /api/me**, Basic Auth aprajita: 

```json
{"username":"aprajita","roles":["ROLE_EMPLOYEE","ROLE_ENGINEERING"]}
```

**POST /api/documents** (Body > form-data): `file` = exported PDF; `visibility` = `PUBLIC` / `PRIVATE` / `TEAM`; `team` = `ENGINEERING` or `HR` (only used for TEAM).

Example **illustrative** response (UUID and number of chunks will differ):

```json
{"documentId":"00000000-0000-0000-0000-000000000001","filename":"employee-policy.pdf","pages":1,"chunks":1,"visibility":"PUBLIC"}
```

**POST /api/ask**, Body > raw JSON:

```json
{"question":"How much annual paid leave do employees receive?"}
```

Example **illustrative** response (model text, distances, UUIDs vary):

```json
{
 "answer":"Employees receive 20 days of annual paid leave each calendar year. [SOURCE 1]",
 "citations":[{"documentId":"00000000-0000-0000-0000-000000000001","filename":"employee-policy.pdf","page":1,"excerpt":"Sample HR Policy ... Employees receive 20 days of annual paid leave ...","distance":0.18}],
 "retrievedChunks":1
}
```

**GET /api/documents** lists documents the authenticated caller may access.

**DELETE /api/documents/{uuid}** deletes a document if caller owns it or has ADMIN role; its chunks cascade-delete.

## Demo user accounts

| User | Username | Password | Role / team |
|---|---|---|---|
| Aprajita | `aprajita` | `aprajita123!` | Employee / Engineering |
| Hrushikesh | `hrushikesh` | `hrushikesh123!` | Employee / Engineering |
| Rekha | `rekha` | `rekha123!` | HR / manager representative |
| Admin | `admin` | `admin123!` | Administrator / all teams |

These accounts and passwords are for **local demo only**. Replace with enterprise identity management for production.

## Permission tests

- Upload a PRIVATE PDF as aprajita. Query/list with rekha: it must not appear (assuming no other accessible PDF contains the same information). Rekha cannot delete Aprajita's document (404).
- Upload TEAM/ENGINEERING as aprajita. Rekha (HR) cannot retrieve its chunks. Aprajita and admin can.
- Upload PUBLIC as aprajita. Both aprajita and rekha can ask questions about it.
- Upload TEAM/HR as aprajita: 403. Admin may upload into any recognized team.
- Retrieval SQL joins `chunks` and `documents` and filters authorization **before** ranking cosine distance. Authorization is not delegated to the LLM.
- No accessible documents: `/api/ask` returns a clear fallback with an empty citations list.

## High-level architecture

```text
 Browser / Postman
       |
 Basic Auth -> Spring Security -> API Controller
                                  |       |
                   PDF upload -> Ingestion service -> PDFBox (page text)
                                  |                 -> text chunks -> EmbeddingModel
                                  |                 -> PostgreSQL documents + pgvector chunks
                                  |
                   Question -> EmbeddingModel -> SQL: visibility/owner/team filtered cosine search
                                                        |
                                       Authorized excerpts only + page numbers
                                                        |
                                                Spring AI ChatClient -> LLM
                                                        |
                                               JSON answer + citations -> UI
```

### Database inspection

```powershell
 "SELECT filename,owner,visibility,team,page_count FROM documents;"
 "SELECT document_id,page_number,left(content,80) FROM chunks;"
```

### Common startup problems

- `relation documents does not exist`: check `flyway_schema_history`; verify Docker database URL and flyway ran successfully.
- `type vector does not exist`: use the included **pgvector/pgvector:pg16** Docker image, not ordinary PostgreSQL. Check `SELECT extname FROM pg_extension`.
- `401 Unauthorized`: incorrect Basic Auth username/password. `403`: role/team permission denied.
- `Connection refused`: check `docker compose ps` and `docker compose logs db`. Check for another local PostgreSQL already using port 5432.
- `401` from OpenAI or DNS issues: verify `OPENAI_API_KEY` and network connectivity. Do not mistake provider 401 for application Basic Auth 401.
- `invalid dimensions`: embeddings must be **1536**; rebuild/reindex if changing embedding models.
- `No extractable text`: scanned PDFs require OCR; this application intentionally supports text-based PDFs only.
- `/api/ask` can incur two external calls: embedding and chat. Upload incurs one embedding operation per chunk.

## Azure OpenAI alternative

In the same PowerShell terminal, configure:

```powershell
$env:AI_PROVIDER="azure-openai"
$env:AZURE_OPENAI_API_KEY="your-azure-key"
$env:AZURE_OPENAI_ENDPOINT="https://your-resource.openai.azure.com/"
$env:AZURE_CHAT_DEPLOYMENT="your-chat-deployment-name"
$env:AZURE_EMBEDDING_DEPLOYMENT="your-embedding-deployment-name"
mvn spring-boot:run
```

Your Azure embedding deployment must emit 1536-dimensional vectors. Azure model config behavior depends on provider deployment type,
review Spring AI Azure reference if your resource requires different API versions. Do not run OpenAI + Azure models simultaneously; 
`AI_PROVIDER` selects the provider.

## Implementation constraints and production improvements

- In-memory demo users and Basic Auth are learning conveniences. Production: SSO/OIDC, JWT validation via Spring Security Resource Server, persistent user/team memberships, immutable tenant IDs, document-level ACLs and audit trail.
- Enforce tenant isolation in the DB (and ideally PostgreSQL row-level security). Current app is single-tenant with simplified teams.
- Current ingestion is synchronous; for real usage use object storage, virus scanning, queue-backed async ingestion, idempotent jobs, bulk embeddings, retry/backoff and dead-letter processing.
- Text extraction ignores images and tables; add OCR and page-image validation when needed.
- Source excerpts are returned from authorized chunks but are not cryptographic proof; implement source verification, relevance cutoffs and citation alignment checks for production.
- PDF uploads are rejected over 15 MB or 150 pages. Add MIME verification, file scanning, rate limiting, API budget controls, and application metrics.
- Prompt-injection mitigation is partial; never trust retrieved text as instructions. Add tests for adversarial PDFs.
- Database row cleanup is best effort if chunk insert fails; production should wrap writes in a transaction / batch.
- Source content is sent to the selected AI provider. Verify corporate data-sharing policies before using sensitive documents.
- Sample responses in this README are **expected examples**, not actual captured run results. This environment could not run Maven, Docker or call a model API.

## Useful references
- https://docs.spring.io/spring-ai/reference/1.1.0/
- https://docs.spring.io/spring-ai/reference/api/vectordbs/pgvector.html
- https://docs.spring.io/spring-ai/reference/api/etl-pipeline.html
