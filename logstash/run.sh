#!/bin/bash

echo "http://localhost:9292/index.html#/dashboard/file/logstash.json"

java -jar logstash-1.3.3-flatjar.jar agent -f simple.conf -- web

