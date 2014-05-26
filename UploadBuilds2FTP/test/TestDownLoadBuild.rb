# This ruby file is to test download latest build to latest folder and delete the older file in this folder
# @author:  StevenGe
# @mail  :  xin-lu.ge@hp.com
# @date  :  2014/03/20
# @version  : 1.0.0-snapshot

require 'test/unit'
require '../lib/devops/DownLoadBuildByAria2c'

class TestDownLoadBuild < Test::Unit::TestCase
  def setup
    @folder_path = '../latestbuild'
    @files_in_folder = Dir.entries(@folder_path)
  end

  def teardown
    # Nothing to do here current now
  end

  #def test_is_file_in_folder
  #  assert_not_equal(['.', '..'], @files_in_folder)
  #end

  def test_delete_build_in_folder
    downloadBuildObj = Devops::DownloadBuildByAria2c.new(@folder_path)
    downloadBuildObj.delete_current_files
    assert_equal(['.', '..'], Dir.entries(@folder_path))
  end

  def test_download_build
    downloadBuildObj = Devops::DownloadBuildByAria2c.new(@folder_path)
    downloadBuildObj.downloadbuild('HPXS-installer-9.50.00-SNAPSHOT-135-win64.zip')
    assert_not_equal(['.', '..'], Dir.entries(@folder_path))
  end

end