<a name="readme-top"></a>
<!-- PROJECT LOGO -->
<br />
<div align="center">

  <h3 align="center">PHANES</h3>

  <p align="center">
    An awesome  project that focus on pure entity component system library!
    <br />
    <a href="#tutorial">Tutorial</a>
    ·
    <a href="https://github.com/crypto-0/com.rdebernard.phanes/issues">Report Bug</a>
    ·
    <a href="https://github.com/crypto-0/com.rdebernard.phanes/pulls">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#tutorial">Tutorial</a></li>
    <li><a href="#projects-using-phanes">projects using phanes</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project


There are many great game engines to quickly build games ranging from 2d to 3d games such unity engine,unreal engine, godot engine, and much more.For me I choosed not to use any libraries or game engine and build myself a library from scratch focusing on entity component system that can be used to make all types of games building it like lego.

Here's why I took on this challenge:
* Building something as complex as a game engine or a library to make video games require a lot of research therefore along the way you learn a lot of things and really push yourself to go past your comfort zone and expand your knowledge of the programming language.
* Knowing how something work at its base core makes it easier to learn other things that are similar and in this case learning different engines becomes easier and faster to grasp.
* Of course the best reason if any is the fun you have along the way and gaining the satification of seing your hard work and hopefully your project helping others.


<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

* java JDK
* java JRE
* maven

### Installation
```sh
$ git clone https://github.com/crypto-0/com.rdebernard.phanes.git
$ cd phanes
$ mvn install
```
<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Tutorial

#### Create components
Components in the phanes library are any class that implements the Component interface
```java
public class RigidBody implements Component{
  public Vec3d velocity ;
  public Vec3d force;
  public float mass;
  public float gravity;

  public RigidBody(){
   velocity = new Vec3d();
   force = new Vec3d();
   mass =1;
   gravity = 9.8f;
  }
  public RigidBody(Vec3d velocity, Vec3d force, float mass, float gravity) {
    this.velocity = velocity;
    this.force = force;
    this.mass = mass;
    this.gravity = gravity;
  }

  public RigidBody(RigidBody rigidBody){
    this.velocity = new Vec3d(rigidBody.velocity);
    this.force = new Vec3d(rigidBody.force);
    this.mass = rigidBody.mass;
    this.gravity = rigidBody.gravity;
  }
}
```
<p align="right">(<a href="#readme-top">back to top</a>)</p>

#### Create Systems
Systems in the phanes library are any class that extends the SystemBase class which also has inner class EntityQuery that can be instatiated to use for filtering and looping of entities with specific components.
```java
public class PhysicSystem extends SystemBase{
  private EntityQuery entityQuery;

  public PhysicSystem(World world){
    super(world);
    entityQuery = new EntityQuery();
    entityQuery.withAll(RigidBody.class,Transform.class);
  }
  @Override
  public void update(float dt) {
    entityQuery.query();
    ArrayList<RigidBody>rigidBodies = entityQuery.getComponents(RigidBody.class);
    ArrayList<Transform>transforms = entityQuery.getComponents(Transform.class);
    for(int a =0; a < rigidBodies.size(); a++){
      Transform transform = transforms.get(a);
      RigidBody rigidBody = rigidBodies.get(a);
      rigidBody.force.add(new Vec3d(0f,rigidBody.gravity * rigidBody.mass,0));
      Vec3d velocity = new Vec3d(rigidBody.velocity);
      velocity.mult(dt);
      transform.position.add(velocity);
      Vec3d  acceleration = new Vec3d(rigidBody.force);
      acceleration.div(rigidBody.mass);
      acceleration.mult(dt);
      rigidBody.velocity.add(acceleration);
      rigidBody.force = new Vec3d();
    }
  }
}

```
#### The world
Everthing happen in the space called world where there are multiple managers for handling entities, entities state machines, components, and scenes. All interactions happen through this one Class world.
```java
public class World{
  public final ComponentManager componentManager;
  public final EntityManager entityManager;
  public final SystemManager systemManager;
  public final PrefabManager prefabManager;
  public final EntityStateMachineManager entityStateMachineManager;
  private final WorldStateMachine worldStateMachine;
  public static final SceneManager sceneManager = new SceneManager();
  public World(){
    componentManager = new ComponentManager(this);
    entityManager = new EntityManager(this);
    systemManager = new SystemManager(this);
    prefabManager = new PrefabManager(this);
    entityStateMachineManager = new EntityStateMachineManager(this);
    worldStateMachine = new WorldStateMachine(this);
  }
}
```
<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Projects using phanes
Flappy Bird


<a href="https://github.com/crypto-0/flappybird">
    <img src="https://github.com/crypto-0/flappybird/blob/master/flappybird.gif?raw=true"> 
</a>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License

Distributed under the MIT License.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [State Machine In ECS](https://www.richardlord.net/blog/ecs/finite-state-machines-with-ash.html)
* [Guided Tutorial On Writing An ECS in C++](https://austinmorlan.com/posts/entity_component_system/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

