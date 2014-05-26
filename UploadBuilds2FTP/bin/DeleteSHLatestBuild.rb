# This ruby file is for users to delete latest build on Shanghai FTP Server
# @author:  StevenGe
# @mail  :  xin-lu.ge@hp.com
# @date  :  2014/05/07
# @version  : 1.0.0-snapshot

require '../lib/devops/Upload2FTP'

sh_ftp_server = '15.154.228.73'
sh_ftp_user = 'auto'
sh_ftp_passwd = 'auto'
sh_ftp_working_dir = '/10_XS950/Nightly/LatestBuild'
build_finder = Devops::Upload2FTP.new(sh_ftp_server, sh_ftp_user, sh_ftp_passwd, sh_ftp_working_dir)
build_finder.connect_ftp
build_finder.delete_files

exit 0