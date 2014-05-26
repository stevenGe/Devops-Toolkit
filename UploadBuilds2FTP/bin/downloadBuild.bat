:: This script is for users to download the build via the aria2c tool
:: author: StevenGe
:: mail  : xin-lu.ge@hp.com
:: date  : 2014/03/20
:: version : 1.0.0-snapshot

@echo off

cd ..\latestbuild
C:\Work\develop\DevopsToolKit\UploadBuilds2FTP\resources\aria2c\aria2c.exe -s 16 -x 16 -j 16 -k 100M ftp://auto:auto@15.154.228.73/10_XS950/Nightly/LatestBuild/%1





