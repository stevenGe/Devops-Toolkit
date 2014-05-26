# This ruby file is to test delete original builds to get more disk space
# @author:  StevenGe
# @mail  :  xin-lu.ge@hp.com
# @date  :  2014/03/31
# @version  : 1.0.0-snapshot

require 'test/unit'
require '../lib/devops/DeleteOldBuilds'

class TestDeleteBuilds < Test::Unit::TestCase
  def setup
    @test_folder = 'C:\Work\test'
  end

  def teardown
    # Nothing to do here current now
  end

  def test_current_files_finder
    finder_obj = Devops::DeleteOldBuilds.new(@test_folder)
    finder_obj.current_files_finder(@test_folder)
    recursively_files = finder_obj.get_recursively_files
    assert_not_equal('', recursively_files.to_s)
  end

  def test_delete_files
    finder_obj = Devops::DeleteOldBuilds.new(@test_folder)
    finder_obj.current_files_finder(@test_folder)
    finder_obj.delete_original_builds
  end

end