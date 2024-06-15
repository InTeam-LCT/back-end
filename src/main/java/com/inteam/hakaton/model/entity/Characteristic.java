package com.inteam.hakaton.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Characteristic {
    @Id
    private Long unom;
    private String area;
    @Column(name = "col_759")
    private Integer col759;
    @Column(name = "col_760")
    private Integer col760;
    @Column(name = "col_761")
    private Integer col761;
    @Column(name = "col_762")
    private Double col762;
    @Column(name = "col_763")
    private Double col763;
    @Column(name = "col_764")
    private Double col764;
    @Column(name = "col_766")
    private Integer col766;
    @Column(name = "col_769")
    private Integer col769;
    @Column(name = "col_770")
    private Integer col770;
    @Column(name = "col_771")
    private Integer col771;
    @Column(name = "col_772")
    private Integer col772;
    @Column(name = "col_775")
    private Integer col775;
    @Column(name = "col_781")
    private Integer col781;
    @Column(name = "col_2463")
    private Integer col2463;
    @Column(name = "col_3163")
    private Integer col3163;

    public Characteristic(Long unom,
                          String area,
                          Integer col759,
                          Integer col760,
                          Integer col761,
                          Double col762,
                          Double col763,
                          Double col764,
                          Integer col766,
                          Integer col769,
                          Integer col770,
                          Integer col771,
                          Integer col772,
                          Integer col775,
                          Integer col781,
                          Integer col2463,
                          Integer col3163) {
        this.unom = unom;
        this.area = area;
        this.col759 = col759;
        this.col760 = col760;
        this.col761 = col761;
        this.col762 = col762;
        this.col763 = col763;
        this.col764 = col764;
        this.col766 = col766;
        this.col769 = col769;
        this.col770 = col770;
        this.col771 = col771;
        this.col772 = col772;
        this.col775 = col775;
        this.col781 = col781;
        this.col2463 = col2463;
        this.col3163 = col3163;
    }
}
