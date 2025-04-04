import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.HashMap;

public class FileManagerController {
//    model: the FileManager object, Model of MVC
//    view: the FileManagerView object, view of the MVC
    private FileManager model;
    private FileManagerView view;

//  Ideally we should be keeping a .log file that stores every single error encountered by the program. Perhaps a seperate class
//  Should exist just to maintain a log.
//  Ryyyykeerrrrr that's on you, I can do it but this sounds kinda fun so its all yours


    public FileManagerController(boolean loadFileManager){
//        Simple constructor, feel free to change it around.

//        loadFileManager: a boolean flag, generally True. Unless you *really* want to make a new FM, careful not to overwite the cache!

        if (loadFileManager){
//            Could definitely make these into smaller functions to clean up this constructor, just a thought.
            try {
                model = FileManagerLoader.load();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally{
//            Log log log!
            }
            view = new FileManagerView();
        }
        else{
            model = new FileManager(new HashSet<Tag>(), new HashMap<Tag,HashSet<File>>(), new HashSet<File>());
            view = new FileManagerView();
        }
    }


}
