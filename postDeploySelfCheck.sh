#!/bin/bash -x
# Author: Rohtash Lakra
PORT=4080
MAX_TIME_IN_SECS=100
API_REQUEST="/rest/identity/?id=0&rows=1"
if [ "$4" = "--debug" ]; then
  set -x
fi
