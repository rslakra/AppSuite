#!/bin/bash
#Author:Rohtash Lakra
clear
echo
# Step 1 - initialize submodules recursively
git submodule update --init --recursive
echo

# Step 2 - - pull submodules recursively
git pull --recurse-submodules
echo

# Step 3 - checkout master branch of submodules recursively (instead of #head branch)
PROJECT_HOME=$PWD
echo
echo "Syncing [${PROJECT_HOME}] ..."
echo
echo
# use for loop to read all values and indexes
for dir in $(pwd)/*/;
do
  dirName=$(basename "${dir}");
  echo "Syncing [${dirName}] ..."
  if [[ ${dirName} != "Infra" ]] ; then
    cd ${dirName}
    git checkout master
    cd ..
  fi
  echo
done
echo
