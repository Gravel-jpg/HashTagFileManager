import java.util.HashSet;

//File objects are very simple, they have a filepath, and a Set of relevant tags

public class File {
//    filepath: a complete filepath to a file Object e.g: 'C/Users/_____/Downloads/asdf.jpg'
//    fileTags: a Set of Tags thats relevant to the File object
//    thumbnailpath: just the name of the thumbnail is thumbnails/ directory
    private String filepath;
    private HashSet<Tag> fileTags;
    private String thumbnailPath;

//Constructors
    public File(FileManager fm, String initFilepath, String initThumbnailPath){
//        Simple constructor for a file with no initial Tags aside from default

        filepath = initFilepath;
        thumbnailPath = initThumbnailPath;
        fileTags = new HashSet<Tag>();
        Tag filepathTag = fm.createTag(initFilepath);
        fileTags.add(filepathTag);
    }
    public File(FileManager fm, String initFilepath, HashSet<Tag> initTags, String initThumbnailPath){
//        A simple constructor for a File with some initial Tags aside from the default

//        initTags: a Set of Tag objects relevant to the file

        filepath = initFilepath;
        thumbnailPath = initThumbnailPath;
        fileTags = initTags;
        Tag pathTag = fm.createTag(initFilepath);
        fileTags.add(pathTag);

    }

//    Functions
    public void addTags(HashSet<Tag> tags){
//        add the passed tags to the local fileTags Set

//        tags: a Set of Tag objects to add to fileTags

        fileTags.addAll(tags);
    }
    public void removeTags(HashSet<Tag> tags){
//        Remove the passed tags to the

//        a Set of Tag objects to be removed from fileTags

        fileTags.removeAll(tags);
    }


//    Overrides
    @Override
    public boolean equals(Object obj){
        if (this == obj){return true;}
        if (!(obj instanceof File other)) return false;
        return this.filepath.equals(other.filepath);
    }
    @Override
    public int hashCode(){return filepath.hashCode();}


    //    Getters and Setters
    public String getFilepath(){return filepath;}
    public void setFilepath(String setPath){filepath = setPath;}
    public HashSet<Tag> getTags(){return fileTags;}
    public void setTags(HashSet<Tag> setTags){fileTags = setTags;}
    public String getThumbnailPath(){return thumbnailPath;}
    public void setThumbnailPath(String setThumbnailPath){thumbnailPath = setThumbnailPath;}
}
