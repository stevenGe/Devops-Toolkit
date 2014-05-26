# This ruby file is to delete original builds to get more disk space
# @author:  StevenGe
# @mail  :  xin-lu.ge@hp.com
# @date  :  2014/03/31
# @version  : 1.0.0-snapshot

module Devops
  class DeleteOldBuilds
    def initialize(delete_parent_folder)
      @delete_parent_folder = delete_parent_folder
      @recursively_files = Hash.new
    end

    def current_files_finder(folder)
      current_path = folder
      files_in_folder = Dir.entries(folder)
      files_in_folder.each do |oneItem|
        if oneItem.eql?('.') || oneItem.eql?('..')
          next
        elsif File.directory?(current_path + '\\' + oneItem)
          current_files_finder(current_path + '\\' + oneItem)
        else
          file_path = current_path + '\\' + oneItem
          @recursively_files[oneItem.to_sym] = file_path
        end
      end
    end

    def delete_original_builds
      @recursively_files.each do|key, value|
        File.delete(value)
      end
    end

    def get_recursively_files
      @recursively_files
    end
  end
end