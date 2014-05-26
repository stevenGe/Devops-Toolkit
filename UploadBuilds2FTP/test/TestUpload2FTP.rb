# This ruby file is to test the function of uploading build to ftp server
# @author:  StevenGe
# @mail  :  xin-lu.ge@hp.com
# @date  :  2014/03/20
# @version  : 1.0.0-snapshot

require 'net/ftp'
require 'test/unit'
require '../lib/devops/Upload2FTP'

class TestUpload2FTP < Test::Unit::TestCase
  def setup
    @ftp_server = '15.154.228.73'
    @ftp_user = 'Steven'
    @ftp_passwd = 'Steven'
    @ftp_working_dir = '/05_PersonalFiles/00_Teammembers/Steven/testFTP'
    @local_working_dir = '../latestbuild'
    @ftp_obj = Devops::Upload2FTP.new(@ftp_server, @ftp_user, @ftp_passwd, @ftp_working_dir, @local_working_dir)
    @ftp_obj.connect_ftp
  end

  def teardown
    @ftp_obj.close_ftp
  end

  def test_connect_to_ftp
    assert_not_nil(@ftp_obj)
    assert_equal("UNIX emulated by FileZilla\n", @ftp_obj.ftp_information)
  end

  def test_delete_current_file
    @ftp_obj.delete_files
  end

  def test_upload_build_to_ftp
    @ftp_obj.upload_build_to_ftp('HPXS-installer-9.50.00-SNAPSHOT-135-win64.zip')
  end

end