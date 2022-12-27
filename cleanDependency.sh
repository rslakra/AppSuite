#/bin/bash
#Author: Rohtash  Lakra
#
# Removed the following folders
# build/target/node_modules
clear
echo
HOME_DIR="${PWD}"
#rm -rf node_modules
nodeModulesPath=""
buildFilePath=""
targetFilePath=""
for entry in */ ; do
  if [[ -d "$entry" && ! -L "$entry" && ! "$entry" == "gradle/" ]]; then
    echo
    nodeModulesPath="${entry}node_modules"
    buildFilePath="${entry}build"
    targetFilePath="${entry}target"
    echo "Removing ${nodeModulesPath}"
    rm -rf $nodeModulesPath
    echo "Removing ${buildFilePath}"
    rm -rf $buildFilePath
    echo "Removing ${targetFilePath}"
    rm -rf $targetFilePath
  fi
done
# move to home dir
cd $HOME_DIR
echo
