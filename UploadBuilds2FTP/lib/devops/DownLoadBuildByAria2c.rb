# This function will download the latest build via aria2c tool
# The latest build would be downloaded to latestbuild folder
# @author:  StevenGe
# @mail  :  xin-lu.ge@hp.com
# @date  :  2014/03/20
# @version  : 1.0.0-snapshot

module Devops
  class DownloadBuildByAria2c

    def initialize(latest_build_folder)
      @latest_build_folder = latest_build_folder
      @download_batch_path = 'C:\Work\develop\DevopsToolKit\UploadBuilds2FTP\bin\downloadBuild.bat'
    end

    def delete_current_files
      files_in_folder = Dir.entries(@latest_build_folder)
      files_in_folder.each do |oneFile|
        if oneFile.eql?('.')
          next
        elsif oneFile.eql?('..')
          next
        else
          delete_file_path = @latest_build_folder + '/' + oneFile
          File.delete(delete_file_path)
        end
      end
    end

    def downloadbuild(build_name)
      system("#{@download_batch_path} #{build_name}")
    end

  end

end

