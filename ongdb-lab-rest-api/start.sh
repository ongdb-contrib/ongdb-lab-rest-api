#!/usr/bin/env bash

# 启动脚本
nohup java -Xmx1024m -Dfile.encoding=utf-8 -jar ./ongdb-lab-rest-api-1.0.0.jar >>logs/ongdb-lab-rest-api.log 2>&1 &

