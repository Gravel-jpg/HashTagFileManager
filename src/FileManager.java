import java.util.HashMap;
import java.util.HashSet;

//This boy here is the meat of the program, going to be responsible for most of the major functions.

public class FileManager {
    //    If a File has no tags, it belongs to the null Set. Only the null Set acts this way, as a marker of untagged videos.
    //    For this reason, nothing in the null Set should belong to any other set.
    //    allFiles: Not sure the implementation of this one, is to be generally avoided unless necessary. Only here incase needed.
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
    public void addFile(String path){
//        Add a file with no existing tags
//        If the file has no tags, it will be added to the null Set
        tagToFileSet.get(null).add(new File(path));
    }
    public void addFile(String path, HashSet<Tag> tags){
//        Add a file with tags.
//        Create the File object and pass it the relevant tags
        File file = new File(path, tags);

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

//    Functions for Tag objects
    public void createTag(String name){
//        Creates a New Tag Object
        Tag tag = new Tag(name);
        existingTags.add(tag);
    }

//    Getters and Setters
    public HashSet<Tag> getExistingTags(){return existingTags;}
    public void setExistingTags(HashSet<Tag> setExistingTags){existingTags = setExistingTags;}
}
