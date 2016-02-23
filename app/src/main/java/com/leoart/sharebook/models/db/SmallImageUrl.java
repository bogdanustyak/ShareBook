
package com.leoart.sharebook.models.db;

public class SmallImageUrl {

    private String content;
    private Boolean nophoto;

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
    public Boolean getNophoto() {
        return nophoto;
    }

    /**
     * 
     * @param nophoto
     *     The nophoto
     */
    public void setNophoto(Boolean nophoto) {
        this.nophoto = nophoto;
    }

}
