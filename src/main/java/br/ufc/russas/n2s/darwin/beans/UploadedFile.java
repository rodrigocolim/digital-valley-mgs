/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.beans;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Wallison Carlos
 */
public class UploadedFile {

    private CommonsMultipartFile file;


    public void setFile(CommonsMultipartFile file)
    {
        this.file = file;
    }

    public CommonsMultipartFile getFile()
    {
        return file;
    }
}
