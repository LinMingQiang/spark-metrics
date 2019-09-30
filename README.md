Grafana + Prometheus

1：安装 Grafana <br> 
wget https://dl.grafana.com/oss/release/grafana-6.3.6-1.x86_64.rpm
sudo yum localinstall grafana-6.3.6-1.x86_64.rpm
service grafana-server start
安装饼图组件：
grafana-cli plugins install grafana-piechart-panel
重启：service grafana-server restart
2：安装 Prometheus <br>
 启动：./prometheus --config.file=prometheus.yml & <br>
 配置文件 ： prometheus.xml （Prometheus采用主动拉去数据。配置的target为对方的ip和port。定时5s去拉取一次数据）<br>
 页面：http://192.168.99.194:9090/graph <br>
 点击target查看配置的所有source。<br>
3： 安装 pushgateway （用于java client 自定义发送） <br>
 启动：./pushgateway (默认9091端口) <br>
 client数据发往pushgateway. -> prometheus 在主动拉去pushgateway。<br>
4：安装 graphite_exporter （用于转发spark GraphiteSink 到 Prometheus）<br> 
（用于spark自带的 GraphiteSink，见配置metrics.properties。可看到spark运行时的jvm内存等状态）<br>
 启动：./graphite_exporter --graphite.mapping-config=spark_mapping （接收数据端口为9109）<br> 
 http://192.168.99.194:9108/metrics 可查看收到的信息<br> 
 spark_mapping : 配置文件用于映射 GraphiteSink -> Prometheus <br> 
 
5：spark 配置文件 metrics.properties
 配置 GraphiteSink 的输出地址。启动的时候带上
./spark-shell \
--files metrics.prop \
--conf spark.metrics.conf=metrics.properties
