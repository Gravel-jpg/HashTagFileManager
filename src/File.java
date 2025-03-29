import java.util.HashSet;

//File objects are very simple, they have a filepath, and a Set of relevant tags

public class File {
    private String path;
    private HashSet<Tag> fileTags;

//Constructors
    public File(String initPath){
        path = initPath;
        fileTags = new HashSet<Tag>();
    }
    public File(String initPath, HashSet<Tag> initTags){
        path = initPath;
        fileTags = initTags;
    }

//    Functions
    public void addTags(HashSet<Tag> tags){
//        add the passed tags to the local fileTags Set
        fileTags.addAll(tags);
    }
    public void removeTags(HashSet<Tag> tags){
//        Remove the passed tags to the
        fileTags.removeAll(tags);
    }


    //    Getters and Setters
    public String getPath(){return path;}
    public void setPath(String setPath){path = setPath;}
    public HashSet<Tag> getTags(){return fileTags;}
    public void setTags(HashSet<Tag> setTags){fileTags = setTags;}
}
