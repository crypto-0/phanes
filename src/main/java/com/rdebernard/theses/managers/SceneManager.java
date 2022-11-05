package com.rdebernard.theses;
import java.util.HashMap;
import java.util.Map;

import com.rdebernard.theses.scenes.Scene;

public class SceneManager{
  World world;
  Map<String,Scene> scenes;
  Scene currentScene;
  public SceneManager(World world){
    scenes = new HashMap<>();
    this.world = world;
  };
  public void addScene(String sceneName, Scene scene){
    scenes.put(sceneName, scene);
  }
  public void removeScene(String sceneName){
    Scene scene= scenes.get(sceneName);
    if(scene !=null){
      scenes.remove(sceneName);
      if(scene == currentScene) currentScene = null;
      scene.onDestroy();
    }
  }
  public void loadScene(String sceneName){
    Scene scene = scenes.get(sceneName);
    if(scene !=null){
      world.clear();
      if(currentScene != null) currentScene.onDeactivate();
      currentScene = scene;
      currentScene.onActivate();
    }
  }
}
