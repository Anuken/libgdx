package com.badlogic.gdx.assets.loaders.resolvers;

import com.badlogic.gdx.Core;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

public class LocalFileHandleResolver implements FileHandleResolver{
    @Override
    public FileHandle resolve(String fileName){
        return Core.files.local(fileName);
    }
}
