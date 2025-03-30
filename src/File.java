import java.util.HashSet;

//File objects are very simple, they have a filepath, and a Set of relevant tags

public class File {
//    Path should be a complete filepath. e.g: 'C/Users/_____/Downloads/asdf.jpg'
    private String path;
    private HashSet<Tag> fileTags;

//Constructors
    public File(FileManager fm, String initPath){
        path = initPath;
        fileTags = new HashSet<Tag>();
        Tag pathTag = fm.createTag(initPath);
        fileTags.add(pathTag);
    }
    public File(FileManager fm, String initPath, HashSet<Tag> initTags){
        path = initPath;
        fileTags = initTags;
        Tag pathTag = fm.createTag(initPath);
        fileTags.add(pathTag);

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


//    Overrides
    @Override
    public boolean equals(Object obj){
        if (this == obj){return true;}
        if (!(obj instanceof File other)) return false;
        return this.path.equals(other.path);
    }
    @Override
    public int hashCode(){return path.hashCode();}


    //    Getters and Setters
    public String getPath(){return path;}
    public void setPath(String setPath){path = setPath;}
    public HashSet<Tag> getTags(){return fileTags;}
    public void setTags(HashSet<Tag> setTags){fileTags = setTags;}
}
