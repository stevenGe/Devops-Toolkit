# This ruby file is to delete original builds to get more disk space
# @author:  StevenGe
# @mail  :  xin-lu.ge@hp.com
# @date  :  2014/03/31
# @version  : 1.0.0-snapshot

require '../lib/devops/DeleteOldBuilds'

builds_folder = 'D:\950'
finder_obj = Devops::DeleteOldBuilds.new(builds_folder)
finder_obj.current_files_finder(builds_folder)
finder_obj.delete_original_builds