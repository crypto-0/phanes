package com.rdebernard.phanes.scenes;
import com.rdebernard.phanes.entities.*;
import java.util.HashMap;
import java.util.Map;

public class SceneManager{
  World world;
  Map<String,Scene> scenes;
  Map<String,Scene> activeScenes;
  public SceneManager(){
    scenes = new HashMap<>();
    activeScenes = new HashMap<>();
  };
  public void addScene(String sceneName, Scene scene){
    scenes.put(sceneName, scene);
  }
  public void removeScene(String sceneName){
    Scene scene= scenes.get(sceneName);
    if(scene !=null){
      scenes.remove(sceneName);
      activeScenes.remove(sceneName);
      scene.onDestroy();
    }
  }
  public void loadScene(String sceneName,Boolean additive){
    Scene scene = scenes.get(sceneName);
    if(scene !=null){
      if( additive){
        if(activeScenes.containsKey(sceneName)){
          activeScenes.get(sceneName).onDeactivate();
        }
        activeScenes.put(sceneName, scene);
        scene.onActivate();
      }
      else{
        for(Scene activeScene: activeScenes.values()){
          activeScene.onDeactivate();
        }
        activeScenes.clear();
        activeScenes.put(sceneName,scene);
        scene.onActivate();
      }
    }
  }
  public void update(float dt){
    for(Scene activeScene: activeScenes.values()){
      activeScene.update(dt);
    }
  }
}
