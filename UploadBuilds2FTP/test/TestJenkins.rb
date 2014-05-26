# This is a test case suit for UploadBuilds2FTP
# @author:  StevenGe
# @mail  :  xin-lu.ge@hp.com
# @date  :  2014/03/20
# @version  : 1.0.0-snapshot

require 'test/unit'
require '../lib/devops/ConnectJenkins'


class TestJenkins < Test::Unit::TestCase
  def setup
    @jenkins_url = '16.157.135.30:8080'
    @latest_uri = '/jenkins/view/Zeus/job/zeus-create-installer_2/lastSuccessfulBuild/artifact/Dist/target/'
    @proxy_host = 'web-proxy.rose.hp.com'
    @proxy_port = 8080
  end

  def teardown
    # Nothing to be implemented here now
  end

  def test_connect_to_jenkins_url
    init_url = Devops::ConnectJenkins.new(@jenkins_url, @latest_uri)
    assert_equal(@jenkins_url, init_url.to_s)
  end

  def test_proxy_settings
    jenkins_connector = Devops::ConnectJenkins.new(@jenkins_url, @latest_uri)
    jenkins_connector.config_proxy(@proxy_host, @proxy_port)
    assert_equal(@proxy_host, jenkins_connector.get_proxy_host)
    assert_equal(@proxy_port, jenkins_connector.get_proxy_port)
  end

  def test_get_response
    jenkins_connector = Devops::ConnectJenkins.new(@jenkins_url, @latest_uri)
    jenkins_connector.config_proxy(@proxy_host, @proxy_port)
    response = jenkins_connector.get_response(@latest_uri)
    assert_equal('200', response.code)
  end

  def test_find_latest_build
    jenkins_connector = Devops::ConnectJenkins.new(@jenkins_url, @latest_uri)
    jenkins_connector.config_proxy(@proxy_host, @proxy_port)
    response = jenkins_connector.get_response(@latest_uri)
    finder = Devops::FindLatestBuild.new(response)
    assert_equal('HPXS-installer-9.50.00-SNAPSHOT-135-win64.zip', finder.find_latest_build)
  end

end