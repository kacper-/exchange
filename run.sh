#!/bin/bash
if test ! -t 0; then
    INPUT=$(java -jar target/exchange-0.0.1-SNAPSHOT-jar-with-dependencies.jar)
    echo "$INPUT"
    echo "$INPUT" | md5sum
else
    echo "No STDIN input"
fi





