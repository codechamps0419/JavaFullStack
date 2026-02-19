/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE: File and Folder system.

A file system naturally forms a tree:

Root Folder
|
+-- resume.pdf
+-- notes.txt
+-- Documents
    |
    +-- java.txt
    +-- spring.txt

Here:
- File   = Leaf object
- Folder = Composite object
- Both are treated through the same FileSystemComponent interface


WITHOUT COMPOSITE (The Problem):
--------------------------------

Suppose our application handles File and Folder separately.

File file = new File(...);
Folder folder = new Folder(...);

if (object is File) {
    file.showDetails();
} else if (object is Folder) {
    folder.showFolderDetails();

    // then manually loop through children
    // check again whether each child is File or Folder
    // repeat recursively...
}

Problems:

1. Client needs to know whether the object is a File or Folder.
2. Client code contains repeated instanceof / if-else checks.
3. Nested folders become difficult to process.
4. Adding another type of file-system object may require client changes.
5. We cannot treat a single object and a group of objects uniformly.


SOLUTION:
---------

Composite Pattern gives File and Folder one common interface:

                    FileSystemComponent
                           /       \
                          /         \
                      File         Folder
                     (Leaf)      (Composite)

The client works only with:

    FileSystemComponent

So it can call:

    component.showDetails();

without caring whether the object is:

- a single File
- a Folder containing many Files
- a Folder containing other Folders

The Folder recursively delegates the same operation to its children.

This is the main idea of Composite Pattern:

"Treat individual objects and groups of objects uniformly."
*/

import java.util.ArrayList;
import java.util.List;

public class CompositePatternDemo {

    public static void main(String[] args) {

        System.out.println("====== FILE SYSTEM USING COMPOSITE PATTERN ======\n");

        // 1. LEAF OBJECTS - Individual files
        
	FileSystemComponent resume =
                new FileItem("Resume.pdf", 120);

        FileSystemComponent photo =
                new FileItem("ProfilePhoto.jpg", 350);

        FileSystemComponent javaNotes =
                new FileItem("Java-Interview-Notes.md", 500);

        FileSystemComponent springNotes =
                new FileItem("Spring-Boot-Notes.md", 700);


        // 2. COMPOSITE OBJECT - A folder can contain files/folders
        Folder documents = new Folder("Documents");

        documents.add(resume);
        documents.add(photo);


        // 3. Another Composite - Nested Folder
        Folder interviewPreparation = new Folder("Interview Preparation");

        interviewPreparation.add(javaNotes);
        interviewPreparation.add(springNotes);


        // A Folder can contain another Folder.
        documents.add(interviewPreparation);


        // 4. Root Composite
        Folder root = new Folder("My Computer");

        root.add(documents);
        root.add(new FileItem("todo.txt", 30));


        // 5. CLIENT
        // Client calls the SAME method on the root component.
        // It does not need to know:
        // - which child is a File
        // - which child is a Folder
        // - how deeply folders are nested
        // Folder handles recursion internally.
        
	root.showDetails("");


        System.out.println("\n------------------------------------------------");
        System.out.println(
                "Total Size: " + root.getSizeInKB() + " KB"
        );
        System.out.println("================================================");
    }
}


// 1. COMPONENT
// Common contract for BOTH:
// - Leaf      -> FileItem
// - Composite -> Folder
// This common interface is the key part of Composite Pattern.

interface FileSystemComponent {

    void showDetails(String indent);

    long getSizeInKB();
}


// 2. LEAF
// Leaf represents an individual object.
// A File has NO children.
// It directly performs the requested operation.

class FileItem implements FileSystemComponent {

    private final String name;
    private final long sizeInKB;

    public FileItem(String name, long sizeInKB) {
        this.name = name;
        this.sizeInKB = sizeInKB;
    }

    @Override
    public void showDetails(String indent) {

        System.out.println(
                indent + "[FILE] " + name + " (" + sizeInKB + " KB)"
        );
    }

    @Override
    public long getSizeInKB() {

        return sizeInKB;
    }
}


// 3. COMPOSITE
//
// Composite represents a GROUP of components.
//
// A Folder can contain:
// FileSystemComponent
//      |
//      +-- FileItem
//      |
//      +-- Folder
//
// Notice:
//
// List<FileSystemComponent>
//
// Because of this common type, Folder does not need separate lists
// for files and sub-folders.

class Folder implements FileSystemComponent {

    private final String name;

    private final List<FileSystemComponent> children =
            new ArrayList<>();


    public Folder(String name) {
        this.name = name;
    }


    // Add either:
    // - FileItem
    // - Folder
    //
    // Both implement FileSystemComponent.
    public void add(FileSystemComponent component) {

        children.add(component);
    }


    public void remove(FileSystemComponent component) {

        children.remove(component);
    }


    @Override
    public void showDetails(String indent) {

        System.out.println(indent + "[FOLDER] " + name);

        /*
         * Important Composite Pattern behavior:
         *
         * Folder does not care whether child is:
         *
         * FileItem
         * OR
         * another Folder
         *
         * It simply calls the SAME operation:
         *
         * child.showDetails(...)
         *
         * If child is a File:
         *      File prints itself.
         *
         * If child is a Folder:
         *      Folder again loops through its children.
         *
         * This naturally creates RECURSION.
         */
        for (FileSystemComponent child : children) {

            child.showDetails(indent + "    ");
        }
    }


    @Override
    public long getSizeInKB() {

        long totalSize = 0;

        /*
         * Same recursive idea for calculating size.
         *
         * File:
         *      returns its own size.
         *
         * Folder:
         *      asks every child for its size.
         *
         * Nested Folder:
         *      repeats the same process recursively.
         */
        for (FileSystemComponent child : children) {

            totalSize += child.getSizeInKB();
        }

        return totalSize;
    }
}


/*

Output:

H:\Design Patterns\Structural> java 
====== FILE SYSTEM USING COMPOSITE PATTERN ======

[FOLDER] My Computer
    [FOLDER] Documents
        [FILE] Resume.pdf (120 KB)
        [FILE] ProfilePhoto.jpg (350 KB)
        [FOLDER] Interview Preparation
            [FILE] Java-Interview-Notes.md (500 KB)
            [FILE] Spring-Boot-Notes.md (700 KB)
    [FILE] todo.txt (30 KB)

------------------------------------------------
Total Size: 1700 KB
================================================

*/