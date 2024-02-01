#!/bin/bash
if test ! -t 0; then
    input=$(java -jar target/exchange-0.0.1-SNAPSHOT-jar-with-dependencies.jar)
    echo $input
    echo $input | md5sum
else
    echo "No STDIN input"
fi





