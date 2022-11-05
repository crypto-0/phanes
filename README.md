<a name="readme-top"></a>
<!-- PROJECT LOGO -->
<br />
<div align="center">

  <h3 align="center">BOLT</h3>

  <p align="center">
    An awesome game project that focus on pure entity component system!
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/crypto-0/com.rdebernard.theses">View Demo</a>
    ·
    <a href="https://github.com/crypto-0/com.rdebernard.theses/issues">Report Bug</a>
    ·
    <a href="https://github.com/crypto-0/com.rdebernard.theses/pulls">Request Feature</a>
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
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project


There are many great game engines to quickly build games ranging from 2d to 3d games such unity engine,unreal engine, godot engine, and much more.For me I choosed not to use any libraries or game engine and build myself a very simple game engine from scratch focusing on entity component system. With this project comes a simple incomplete dog game that showcase everything working together such as the dog changing its states from idle to running or attacking and systems such as pysics system, collison systems and animation working on its own components to get things done.

Here's why I took on this challenge:
* Building something as complex as a game engine require a lot of research therefore along the way you learn a lot of things and really push yourself to go past your comfort zone and expand your knowledge of the programming language.
* Knowing how something work at its base core makes it easier to learn other things that are similar and in this case learning different engines becomes easier and faster to grasp.
* Of course the best reason if any is the fun you have along the way and gaining the satification of seing your hard work and hopefully your project helping others.


<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- GETTING STARTED -->
## Getting Started


### Prerequisites

* java JDK
* java JRE

### Installation
1. Clone source code 
    ```sh
    $ git clone https://github.com/crypto-0/com.rdebernard.theses.git
    ```
<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage

1. Compile java source code 
    ```sh
    $ javac -d target -sourcepath . src/main/java/com.rdebernard.theses/*.java src/main/java/com.rdebernard.theses/components/*.java src/main/java/com.rdebernard.theses/systems/*.java src/main/java/com.rdebernard.theses/scenes/*.java  src/main/java/com.rdebernard.theses/Bolt.java
    ```
2. Run the created java package
    ```sh
   $ java -cp src/main/resources/:target/ com.rdebernard.theses.Bol
    ```
<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ROADMAP -->
## Roadmap

- [x] Add base components
- [x] Add base managers such as component, entity, system, and asset manager.
- [x] Add base systems such as physics, animation, collision detections, and collision resolution systems.
- [ ] Add UI components and UI systems.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License

Distributed under the MIT License.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [State Machine In ECS](https://www.richardlord.net/blog/ecs/finite-state-machines-with-ash.html)
* [Guided Tutorial On Writing An ECS in C++](https://austinmorlan.com/posts/entity_component_system/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

