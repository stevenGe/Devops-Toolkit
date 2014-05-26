# This ruby file is for users to Download builds and upload builds to Israel FTP Server
# @author:  StevenGe
# @mail  :  xin-lu.ge@hp.com
# @date  :  2014/03/21
# @version  : 1.0.0-snapshot

require '../lib/devops/ConnectJenkins'
require '../lib/devops/DownLoadBuildByAria2c'
require '../lib/devops/Upload2FTP'

#jenkins_url = '16.157.135.30:8080'
#latest_uri = '/jenkins/view/Zeus/job/zeus-create-installer_2/lastSuccessfulBuild/artifact/Dist/target/'
#proxy_host = 'web-proxy.rose.hp.com'
#proxy_port = 8080

#connect_jenkins = Devops::ConnectJenkins.new(jenkins_url, latest_uri)
#connect_jenkins.config_proxy(proxy_host, proxy_port)
#jenkins_response = connect_jenkins.get_response(latest_uri)
#
#build_name_finder = Devops::FindLatestBuild.new(jenkins_response)
#build_name = build_name_finder.find_latest_build


sh_ftp_server = '15.154.228.73'
sh_ftp_user = 'auto'
sh_ftp_passwd = 'auto'
sh_ftp_working_dir = '/10_XS950/Nightly/LatestBuild'
build_finder = Devops::Upload2FTP.new(sh_ftp_server, sh_ftp_user, sh_ftp_passwd, sh_ftp_working_dir)
build_finder.connect_ftp
build_name = build_finder.find_latest_build_from_sh_ftp


folder_path = '../latestbuild'
build_downloader = Devops::DownloadBuildByAria2c.new(folder_path)
build_downloader.delete_current_files
build_downloader.downloadbuild(build_name)

ftp_server = '16.44.48.137'
ftp_user = 'agora'
ftp_passwd = 'agora'
ftp_working_dir = '/BTOA/9.5/9.5_installer'
local_working_dir = '../latestbuild'
ftp_uploader = Devops::Upload2FTP.new(ftp_server, ftp_user, ftp_passwd, ftp_working_dir, local_working_dir)
ftp_uploader.connect_ftp
ftp_uploader.delete_files
ftp_uploader.upload_build_to_ftp(build_name)
ftp_uploader.close_ftp

exit 0

