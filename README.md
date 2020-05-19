Grafana + Prometheus <br>
```
关闭 防火墙 ： sudo systemctl disable firewalld <br>
install Grafana <br> 
 wget https://dl.grafana.com/oss/release/grafana-6.3.6-1.x86_64.rpm <br>
 sudo yum localinstall grafana-6.3.6-1.x86_64.rpm <br>
 service grafana-server start <br>
 安装饼图组件： <br>
 grafana-cli plugins install grafana-piechart-panel <br>
 重启：service grafana-server restart <br>
```
```
install Prometheus
 启动：./prometheus --config.file=prometheus.yml & <br>
 配置文件 ： prometheus.xml （Prometheus采用主动拉去数据。配置的target为对方的ip和port。定时5s去拉取一次数据）<br>
 页面：http://192.168.99.194:9090/graph <br>
 点击target查看配置的所有source。<br>
```
```
install pushgateway （用于java client 自定义发送） <br>
 启动：./pushgateway (默认9091端口) <br>
 client数据发往pushgateway. -> prometheus 在主动拉去pushgateway。<br>
```
```
install graphite_exporter （用于转发spark GraphiteSink 到 Prometheus）<br> 
（用于spark自带的 GraphiteSink，见配置metrics.properties。可看到spark运行时的jvm内存等状态）<br>
 启动：./graphite_exporter --graphite.mapping-config=spark_mapping （接收数据端口为9109）<br> 
 http://192.168.99.194:9108/metrics 可查看收到的信息<br> 
 spark_mapping : 配置文件用于映射 GraphiteSink -> Prometheus <br> 
```
```
spark 配置文件 metrics.properties <br>
 配置 GraphiteSink 的输出地址。启动的时候带上 <br>
linux : spark-shell --files metrics.properties --conf spark.metrics.conf=metrics.properties <br>
windows :  <br>
spark-shell --files G:\intellijideaspace\zzy\samsung\spark-metrics\conf\metrics.properties --conf spark.metrics.conf=G:\intellijideaspace\zzy\samsung\spark-metrics\conf\metrics.properties
```
