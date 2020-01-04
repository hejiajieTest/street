服务脚本用法：

1. linux环境下

1）启动服务

#./startServer.sh -p 5100

2）停止服务

#./stopServer.sh

3）设置服务自启动

#cp cloudui /etc/init.d/
#chkconfig cloudui on

#手动启动
# /etc/init.d/dcloudui start
# /etc/init.d/cloudui stop


注意：默认java和服务程序包都安装到了linux /opt/目录下，
      如果路径有变化，请修改。

2. windows环境下

1) 启动服务

#./startServer.bat
