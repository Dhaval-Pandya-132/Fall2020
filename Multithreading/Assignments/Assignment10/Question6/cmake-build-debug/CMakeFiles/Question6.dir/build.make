# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.17

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake

# The command to remove a file.
RM = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/Question6.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Question6.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Question6.dir/flags.make

CMakeFiles/Question6.dir/main.cpp.o: CMakeFiles/Question6.dir/flags.make
CMakeFiles/Question6.dir/main.cpp.o: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/Question6.dir/main.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/Question6.dir/main.cpp.o -c /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/main.cpp

CMakeFiles/Question6.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Question6.dir/main.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/main.cpp > CMakeFiles/Question6.dir/main.cpp.i

CMakeFiles/Question6.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Question6.dir/main.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/main.cpp -o CMakeFiles/Question6.dir/main.cpp.s

CMakeFiles/Question6.dir/Student.cpp.o: CMakeFiles/Question6.dir/flags.make
CMakeFiles/Question6.dir/Student.cpp.o: ../Student.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/Question6.dir/Student.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/Question6.dir/Student.cpp.o -c /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/Student.cpp

CMakeFiles/Question6.dir/Student.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Question6.dir/Student.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/Student.cpp > CMakeFiles/Question6.dir/Student.cpp.i

CMakeFiles/Question6.dir/Student.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Question6.dir/Student.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/Student.cpp -o CMakeFiles/Question6.dir/Student.cpp.s

# Object files for target Question6
Question6_OBJECTS = \
"CMakeFiles/Question6.dir/main.cpp.o" \
"CMakeFiles/Question6.dir/Student.cpp.o"

# External object files for target Question6
Question6_EXTERNAL_OBJECTS =

Question6: CMakeFiles/Question6.dir/main.cpp.o
Question6: CMakeFiles/Question6.dir/Student.cpp.o
Question6: CMakeFiles/Question6.dir/build.make
Question6: CMakeFiles/Question6.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Linking CXX executable Question6"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/Question6.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/Question6.dir/build: Question6

.PHONY : CMakeFiles/Question6.dir/build

CMakeFiles/Question6.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/Question6.dir/cmake_clean.cmake
.PHONY : CMakeFiles/Question6.dir/clean

CMakeFiles/Question6.dir/depend:
	cd /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6 /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6 /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/cmake-build-debug /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/cmake-build-debug /Users/mrcricket/Desktop/Fall2020/Multithreading/Assignments/Assignment10/Question6/cmake-build-debug/CMakeFiles/Question6.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/Question6.dir/depend

