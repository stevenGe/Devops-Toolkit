# This ruby file is for users to connect to jenkins server
# @author:  StevenGe
# @mail  :  xin-lu.ge@hp.com
# @date  :  2014/03/20
# @version  : 1.0.0-snapshot

require 'net/http'

module Devops
  class ConnectJenkins
    def initialize(jenkins_url, latest_uri)
      @jenkins_url = jenkins_url
      @latest_uri = latest_uri
    end

    def to_s
      @jenkins_url
    end

    def get_response(latest_uri, limit = 5)
      fail 'http redirect too deep' if limit.zero?
      @proxy.start(@jenkins_url) do |http|
        response = http.get(latest_uri)
        case response
          when Net::HTTPSuccess then @response = response
          when Net::HTTPRedirection then @response = get_response(response['location'], limit - 1)
          else response.error!
        end
      end
    end

    def config_proxy(proxy_host, proxy_port)
      @proxy_host = proxy_host
      @proxy_port = proxy_port
      @proxy = Net::HTTP::Proxy(@proxy_host, @proxy_port)
    end

    def get_proxy_host
      @proxy_host
    end

    def get_proxy_port
      @proxy_port
    end
  end

  class FindLatestBuild
    def initialize(response)
      @response = response
    end
    def find_latest_build
      response_body = @response.body
      pattern = /zeus-create-installer_2 #/
      index = (pattern =~ response_body) + 'zeus-create-installer_2 #'.size
      pattern_last = / : /
      index_last = (pattern_last =~ response_body)
      build_number = response_body[index, index_last - index]
      build_name = 'HPXS-installer-9.50.00-SNAPSHOT-' + build_number.chomp + '-win64.zip'
    end
  end

end