import java.util.HashMap;
import java.util.HashSet;

//This boy here is the meat of the program, going to be responsible for most of the major functions.

public class FileManager {
//    If a File has no tags, it belongs to the null Set. Only the null Set acts this way, as a marker of untagged videos.
//    For this reason, nothing in the null Set should belong to any other set.
//    existingTags: a Set of all currently existing Tag objects
//    tagToFileSet: A HashSet which maps a Tag object to a Set of Files corresponding to that Tag objects.
//    allFiles: Not sure the implementation of this one, is to be generally avoided unless necessary. Only here incase needed in the future.
    private HashSet<Tag> existingTags;
    private HashMap<Tag,HashSet<File>> tagToFileSet;
    private HashSet<File> allFiles;


//    Constructor
    public FileManager(HashSet<Tag> initExistingTags, HashMap<Tag,HashSet<File>> initTagToFileSet, HashSet<File> initAllFiles){
//        This is probably useless going forward(?)
//        I want this to open from a save file, creating a completely new FileManager should generally be avoided.
//        After "Installation" this specific constructor should never really be called.
//        But we're testing now, soooo its fine.....
        existingTags = initExistingTags;
        tagToFileSet = initTagToFileSet;
        allFiles = initAllFiles;
    }


//    Functions for file objects
    public void addFile(String path, FileManager fm){
//        Add a file with no tags other than its default pathTag
//        Handled by the File constructor
        File file = new File(this,path);

    }
    public void addFile(String path, HashSet<Tag> tags, FileManager fm){
//        Add a file with passed tags.
//        Files always have a tag of their path.
//        Create the File object and pass it the relevant tags
        File file = new File(this, path, tags);
//        The File constructor already adds the new files pathTag to the tagToFileSet Map and the existingTags Set

//        Iterate through every tag, make sure the Tags point towards the file
        for (Tag tag: tags){
            tagToFileSet.get(tag).add(file);
        }



    }
    public void addFileTag(File file, HashSet<Tag> tags){
//        Add tags to a file
//        First, add the tags to the File objects tag Set
        file.addTags(tags);
//        Then, add the file to the Tags File Sets
        for (Tag tag: tags){
            tagToFileSet.get(tag).add(file);
        }
    }
    public void removeFileTag(File file, HashSet<Tag> tags){
//        remove tags from a file
//        First, remove the tags from the File objects tag Set
        file.removeTags(tags);
//        Then, remove the file from the Tags File Sets
        for (Tag tag: tags){
            tagToFileSet.get(tag).remove(file);
        }

    }
    public HashSet<File> getFilesWithTags(HashSet<Tag> tags){
//        Should return the Intersection of all tags
        HashSet<File> fileSet = new HashSet<File>();
        boolean firstPass = true;
        for (Tag tag: tags){
//            This is kind of an ugly solution, but it should work. Initializes fileSet as the first iterated set.
//            Then it iterates through, using retainAll to remove any files that don't appear in the next to produce the intersection
            if (firstPass){
                fileSet.addAll(tagToFileSet.get(tag));
            }
            else{
                fileSet.retainAll(tagToFileSet.get(tag));
//                If the fileSet is ever empty, exit early to save time
                if (fileSet.isEmpty()){
                    return fileSet;
                }
            }

        }
        return fileSet;

    }


//    Functions for Tag objects
    public Tag createTag(String name){
//        Creates a New Tag Object
//        Adds to existingTags and tagToFileSet
//        Can't create duplicate tags.
        Tag tag = new Tag(name);
        if (!(existingTags.contains(tag))){
            existingTags.add(tag);
            tagToFileSet.put(tag,new HashSet<File>());
        }
//        This should be changed to throw an actual exception. This is fine for early days.
        else
        {System.out.println("ERROR: tag already exists");}
        return tag;
    }
    public void removeTag(String name){
//        To remove a tag from the program
//        1: remove the tag from all files with it
        Tag tag = new Tag(name);
//        Not particularly fond of this workaround for create the HashSet of tags to be removed, there is probably a
//        More elegant one-line solution to initialize the set with a Tag object already in it
        HashSet<Tag> tagSet = new HashSet<Tag>();
        tagSet.add(tag);
        for (File file: tagToFileSet.get(tag)){
            file.removeTags(tagSet);
        }
//        2: remove the tag from the HashMap
        tagToFileSet.remove(tag);
//        3: remove the tag from existingTags
        existingTags.remove(tag);
    }


//    Getters and Setters
    public HashSet<Tag> getExistingTags(){return existingTags;}
    public void setExistingTags(HashSet<Tag> setExistingTags){existingTags = setExistingTags;}
}
