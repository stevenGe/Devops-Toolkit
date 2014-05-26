# This ruby file is for users to upload build to ftp server
# This function will delete the original files
# @author:  StevenGe
# @mail  :  xin-lu.ge@hp.com
# @date  :  2014/03/21
# @version  : 1.0.0-snapshot

require 'net/ftp'

module Devops
  class Upload2FTP

    def initialize(ftp_server, ftp_user, ftp_passwd, ftp_work_folder, local_work_folder = nil)
      @ftp_server = ftp_server
      @ftp_user = ftp_user
      @ftp_passwd = ftp_passwd
      @ftp_work_folder = ftp_work_folder
      @local_work_folder = local_work_folder
      @ftp = nil
    end

    def connect_ftp
      @ftp = Net::FTP.new(@ftp_server)
      @ftp.login(@ftp_user, @ftp_passwd)
    end

    def delete_files
      @ftp.chdir(@ftp_work_folder)
      puts @ftp.nlst
      file_names = @ftp.nlst
      file_names.each do |each_file|
        if each_file.eql?('.')
          next
        elsif each_file.eql?('..')
          next
        else
          @ftp.delete(each_file)
        end
      end
    end

    def find_latest_build_from_sh_ftp
      @ftp.chdir(@ftp_work_folder)
      file_names = @ftp.nlst
      @latest_build_name_on_ftp = nil
      file_names.each do |each_file|
        if each_file.eql?('.')
          next
        elsif each_file.eql?('..')
          next
        else
          @latest_build_name_on_ftp = each_file
        end
      end
    end

    def upload_build_to_ftp(build_name)
      @ftp.chdir(@ftp_work_folder)
      @ftp.putbinaryfile(@local_work_folder + "/#{build_name}", build_name)
    end

    def close_ftp
      @ftp.closed? ? nil : @ftp.close
    end

    def ftp_information
      @ftp.system
    end

  end
end