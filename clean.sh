#!/bin/bash
#Author: Rohtash Lakra
echo
HOME_DIR="${PWD}"
#echo "Working at ${HOME_DIR}"
echo
MODULES=("Core" "Gemini" "HBase" "Identity" "Jdk" "Metrics" "Patterns" "Questions")
for entry in "${MODULES[@]}" 
do
  curEntry="${HOME_DIR}/${entry}/target"
  echo "Cleaning ... ${curEntry}"
  rm -rf $curEntry
  echo
done
# move to home dir
cd $HOME_DIR
echo

