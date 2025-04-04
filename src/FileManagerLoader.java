import java.io.*;
import java.util.HashSet;
import java.util.HashMap;

public class FileManagerLoader {
//    CACHE_PATH: exactly what it sounds like, just to shorten some of the strings later.
    private static final String CACHE_PATH = "cache/";

//    Functions to initiate the save/load calls
    public static FileManager load ()throws IOException, ClassNotFoundException, FileNotFoundException{
//        A function that attempts to create a FileManager object from relevant .ser files, assumes they exist.
//        Sorry Ryker! wanted to keep this simpler for you, but this makes a lot of sense to me.

//        loadFailed: A simple Set to keep track of what did and did not load correctly.
//        initExistingTags: the init value for the FileManager object. If loading fails, will be null.
//        initTagToFileSet: ^
//        InitAllFiles: ^

        HashSet<String> loadFailed = new HashSet<String>();
        HashSet<Tag> initExistingTags;
        HashMap<Tag,HashSet<File>> initTagToFileSet;
        HashSet<File> initAllFiles;


        try {
            initExistingTags = loadTags();
        }catch (Exception e){
            initExistingTags = null;
            loadFailed.add("tags.ser");
        }

        try{
            initTagToFileSet = loadTagmap();
        }catch (Exception e){
            initTagToFileSet = null;
            loadFailed.add("tagmap.ser");
        }

        try{
            initAllFiles = loadFiles();
        }catch (Exception e){
            initAllFiles = null;
            loadFailed.add("files.ser");
        }

        if (!(loadFailed.isEmpty())){
            throw new IOException("Failed to load: "+String.join(", ",loadFailed));
        }

        return new FileManager(initExistingTags, initTagToFileSet, initAllFiles);
    }
    public static void save(FileManager FM){
//        A function to save a FileManager object across several .ser files
//        I'm considering defining a new Exception, then having this throw it. So that it's the Controllers job to log
//        Or to call the logger, either way I don't like the inconsistency between load and save

//        FM: the FileManager object in question
//        saveFailed: a simple Set to keep track of what did and did not save correctly.

        HashSet<String> saveFailed = new HashSet<String>();

        try{
            saveTags(FM.getExistingTags());
        }catch(Exception e){
            saveFailed.add("tags.ser");
        }

        try{
            saveTagmap(FM.getTagToFileSet());
        }catch (Exception e){
            saveFailed.add("tagmap.ser");
        }

        try{
            saveFiles(FM.getAllFiles());
        }catch (Exception e){
            saveFailed.add("files.ser");
        }

        if (!(saveFailed.isEmpty())){
            throw new RuntimeException("Failed to save: "+String.join(", ",saveFailed));
//            Log Log Log!!!
        }
    }

//    Functions to load the individual .ser files
    public static HashSet<Tag> loadTags() throws IOException, ClassNotFoundException, FileNotFoundException {
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(CACHE_PATH+"tags.ser"));
            HashSet<Tag> initExistingTags = (HashSet<Tag>) inputStream.readObject();
            inputStream.close();
            return initExistingTags;
        }finally{
//            Haven't found a good way to make these finally blocks work yet. Will come back.
        }

    }
    public static HashMap<Tag,HashSet<File>> loadTagmap() throws IOException, ClassNotFoundException, FileNotFoundException{
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(CACHE_PATH+"tagmap.ser"));
            HashMap<Tag,HashSet<File>> initTagToFileSet = (HashMap<Tag,HashSet<File>>) inputStream.readObject();
            inputStream.close();
            return initTagToFileSet;
        }finally{
//            Haven't found a good way to make these finally blocks work yet. Will come back.
        }

    }
    public static HashSet<File> loadFiles()throws IOException, ClassNotFoundException, FileNotFoundException{
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(CACHE_PATH+"files.ser"));
            HashSet<File> initAllFiles = (HashSet<File>) inputStream.readObject();
            inputStream.close();
            return initAllFiles;
        }finally{
//            Haven't found a good way to make these finally blocks work yet. Will come back.
        }

    }

//    Functions to save the individual .ser files
    public static void saveTags(HashSet<Tag> tags) throws IOException, FileNotFoundException{
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(CACHE_PATH+"tags.ser"));
            outputStream.writeObject(tags);
            outputStream.close();
        }finally{
//            Haven't found a good way to make these finally blocks work yet. Will come back.
        }
    }
    public static void saveTagmap(HashMap<Tag,HashSet<File>> tagmap ) throws IOException, FileNotFoundException{
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(CACHE_PATH+"tagmap.ser"));
            outputStream.writeObject(tagmap);
            outputStream.close();
        }finally{
//            Haven't found a good way to make these finally blocks work yet. Will come back.
        }
    }
    public static void saveFiles(HashSet<File> files) throws IOException, FileNotFoundException{
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(CACHE_PATH+"files.ser"));
            outputStream.writeObject(files);
            outputStream.close();
        }finally{
//            Haven't found a good way to make these finally blocks work yet. Will come back.
        }
    }
}
