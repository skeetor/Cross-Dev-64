# CrossDev64 - A C64/65xx cross-development IDE

CrossDev64 is an IDE for C64 assembler-coding on Windows, Linux other OSs running Java.
CrossDev64 offers a modern IDE inspired by popular frameworks i.e. like Visual Studio from Microsoft.

Java 7 or higher is required to run CrossDev64.  

This editor works together with common cross assemblers. Currently supported assemblers are 64tass, ACME, ca65, DASM, DreamAss, Kick Assembler and TMPx. Other assemblers might work as well, but syntax highlighting may not be 100% correct.

# Acknowledgments

Code is partially taken from [Relaunch64](https://github.com/sjPlot/Relaunch64)  

The icons and graphics is taken from external sources with a free license. However those are not covered under the GPL and may have their own license.

Application icon made by Freepik from [https://www.flaticon.com](https://www.flaticon.com/free-icon/commodore_447535#term=commodore&page=1&position=4)
Various free icons taken from [http://www.small-icons.com](http://www.small-icons.com/stock-icons/24x24-free-application-icons.htm)

# Planned features

These major features are planned for future releases. The order reflects the intended priority.

* Mechanics for shortcuts to menus and features.
* Project specific settings
* Debugger for stepping through code with an emulator
* Support for projects
* Sprite editor
* Character editor
* Sound editor(?)
* Apply mechanics for settings 

# Completed features

* Global preferences mechanics.
* Allow running multiple instances with their own individual home directories.
* Add support for dockable windows, so users can move around the windows as they want.
* Support for localisation

# License

The program is licensed under the GNU GPL 3 (or higher) and free for private and professional use. You should have gotten a copy of the GPL 3 along with this program. If not, you can download it from [www.gnu.org](https://www.gnu.org/licenses/gpl-3.0.en.html)  

# Documentation

# Building

In order to build, you will need to download the JAR files listed below. Newer versions may be used, but is 
untested. Those are the ones used for development, so they should work for sure.

# Required external JARs

The project was built, using these jar files. Newer versions may work as well.

* [docking-frames-common.jar](http://www.docking-frames.org/)
* [docking-frames-core.jar](http://www.docking-frames.org/)
* [jackson-annotations-2.9.5.jar](http://central.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.9.5/jackson-annotations-2.9.5.jar)
