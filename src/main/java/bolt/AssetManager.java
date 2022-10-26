package bolt;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AssetManager{
  private Map<String ,Sprite> sprites;
  private Map<String, SpriteSheet> spriteSheets;
  private Logger logger;
  public AssetManager(){
    sprites = new HashMap<>();
    spriteSheets = new HashMap<>();
    logger = Logger.getLogger(AssetManager.class.getName());

  }
  public void addSprite(String resourceName,Sprite sprite){
    String path = this.getClass().getClassLoader().getResource(resourceName).getPath();
    if(!sprites.containsKey(path)){
      if(sprite !=null)sprites.put(path, sprite);
    }
  }
  public void addSpriteSheet(String resourceName,SpriteSheet spriteSheet){
    //String path = this.getClass().getClassLoader().getResource(resourceName).getPath();
    if(!spriteSheets.containsKey(resourceName)){
      if(spriteSheet !=null)spriteSheets.put(resourceName, spriteSheet);
    }
  }

  public Sprite getSprite(String resourceName){
    String path = this.getClass().getClassLoader().getResource(resourceName).getPath();
    Sprite sprite = sprites.get(path);
    if(sprite == null){
      this.logger.warning("Tried to access sprite " + path + " and its not managed");
    }
    return sprite;
  }
  public SpriteSheet getSpriteSheet(String resourceName){
    //String path = this.getClass().getClassLoader().getResource(resourceName).getPath();
    SpriteSheet spriteSheet = spriteSheets.get(resourceName);
    if(spriteSheet == null){
      this.logger.warning("Tried to access spritesheet " + resourceName + " and its not managed");
    }
    return spriteSheet;
  }

  public void clearAssets(){
    sprites.clear();
    spriteSheets.clear();
  }
}
