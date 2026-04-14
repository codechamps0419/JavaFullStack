CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE documents (
 id UUID PRIMARY KEY, filename TEXT NOT NULL, owner TEXT NOT NULL,
 visibility TEXT NOT NULL CHECK (visibility IN ('PRIVATE','PUBLIC','TEAM')),
 team TEXT, page_count INTEGER NOT NULL, created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE chunks (
 id UUID PRIMARY KEY, document_id UUID NOT NULL REFERENCES documents(id) ON DELETE CASCADE,
 page_number INTEGER NOT NULL, chunk_number INTEGER NOT NULL,
 content TEXT NOT NULL, embedding vector(1536) NOT NULL
);

CREATE INDEX idx_chunks_document ON chunks(document_id);

CREATE INDEX idx_docs_owner ON documents(owner);
-- HNSW is useful as the corpus grows; exact filtered query is used for access-safe retrieval.

/*
 postgres=# create database entknowledge;
CREATE DATABASE
postgres=# \c entknowledge
You are now connected to database "entknowledge" as user "postgres".
entknowledge=# CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION
entknowledge=# CREATE TABLE documents (
entknowledge(#  id UUID PRIMARY KEY, filename TEXT NOT NULL, owner TEXT NOT NULL,
entknowledge(#  visibility TEXT NOT NULL CHECK (visibility IN ('PRIVATE','PUBLIC','TEAM')),
entknowledge(#  team TEXT, page_count INTEGER NOT NULL, created_at TIMESTAMPTZ NOT NULL DEFAULT now()
entknowledge(# );
CREATE TABLE
entknowledge=# CREATE TABLE chunks (
entknowledge(#  id UUID PRIMARY KEY, document_id UUID NOT NULL REFERENCES documents(id) ON DELETE CASCADE,
entknowledge(#  page_number INTEGER NOT NULL, chunk_number INTEGER NOT NULL,
entknowledge(#  content TEXT NOT NULL, embedding vector(1536) NOT NULL
entknowledge(# );
CREATE TABLE
entknowledge=# CREATE INDEX idx_chunks_document ON chunks(document_id);
CREATE INDEX
entknowledge=# CREATE INDEX idx_docs_owner ON documents(owner);
CREATE INDEX
entknowledge=#
entknowledge=#

 */