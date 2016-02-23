
package com.leoart.sharebook.models.db;

public class ImageUrl {

    private String content;
    private boolean nophoto;

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The nophoto
     */
    public boolean isNophoto() {
        return nophoto;
    }

    /**
     * 
     * @param nophoto
     *     The nophoto
     */
    public void setNophoto(boolean nophoto) {
        this.nophoto = nophoto;
    }

}
