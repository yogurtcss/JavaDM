package pers.yo.service;

import pers.yo.entity.Page;

public interface DownloadService {
    public abstract Page download( String url );
}
