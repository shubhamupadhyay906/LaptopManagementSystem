package com.example.demo.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Stocks implements Comparator<Stocks>, Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	private String productName;
	private int perPrice;
	private String type;
	private int totalStock;
	@Override
	public int compare(Stocks o1, Stocks o2) {
		int compareto = o1.productName.compareTo(o2.productName);
		if(compareto==0) {
			return o1.perPrice-o2.perPrice;
		}
		return compareto;
	}
}
