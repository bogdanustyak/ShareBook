
package com.leoart.sharebook.models.db;

public class Author {

    private Double averageRating;
    private Integer id;
    private ImageUrl imageUrl;
    private String name;
    private SmallImageUrl smallImageUrl;

    /**
     * 
     * @return
     *     The averageRating
     */
    public Double getAverageRating() {
        return averageRating;
    }

    /**
     * 
     * @param averageRating
     *     The average_rating
     */
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The imageUrl
     */
    public ImageUrl getImageUrl() {
        return imageUrl;
    }

    /**
     * 
     * @param imageUrl
     *     The image_url
     */
    public void setImageUrl(ImageUrl imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The smallImageUrl
     */
    public SmallImageUrl getSmallImageUrl() {
        return smallImageUrl;
    }

    /**
     * 
     * @param smallImageUrl
     *     The small_image_url
     */
    public void setSmallImageUrl(SmallImageUrl smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

}
