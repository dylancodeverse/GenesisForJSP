package modeles;

import jakarta.persistence.* ;


@Entity
@Table(name = "promotion")        

public class PromotionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "name", nullable = false)    
    private String name ;

    @Column(name = "year", nullable = false)    
    private Integer year ;


    public void setId (Integer value) {
        this.Id= value ;
    }

    public void setName (String value) {
        this.Name= value ;
    }

    public void setYear (Integer value) {
        this.Year= value ;
    }


    public Integer getId () {
        return this.Id ;
    }

    public String getName () {
        return this.Name ;
    }

    public Integer getYear () {
        return this.Year ;
    }


    }

