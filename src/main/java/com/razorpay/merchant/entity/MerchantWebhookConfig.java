package com.razorpay.merchant.entity;

import java.util.UUID;

import com.razorpay.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "merchant_webhook_config",indexes = {
		@Index(name = "idx_webhook_merchant_id", columnList = "merchant_id, enabled")
})
public class MerchantWebhookConfig extends BaseEntity{
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.UUID)
	    private UUID id;

	    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "merchant_id", nullable = false)
	    private Merchant merchant;

	    @Column(nullable = false, length = 500)
	    private String targetUrl; //www.zara.com/webhook/success

	    @Column(length = 255)
	    private String webhookSecret;

	    @Column(nullable = false)
	    private Boolean enabled = true;

	    @Column(length = 255)
	    private String eventTypes;
	    // Comma-separated list of event types to subscribe to

	    public boolean isSubscribedTo(String eventType) {
	        if (eventTypes == null || eventTypes.isBlank()) {
	            return true;
	        }
	        for (String type : eventTypes.split(",")) {
	            String trimmed = type.trim();
	            if (trimmed.equalsIgnoreCase("ALL") || trimmed.equalsIgnoreCase(eventType)) {
	                return true;
	            }
	        }
	        return false;
	    }

}
