/*
package com.ms.bpp.services;

import com.ms.bpp.dao.ProvidersRepo;
import com.ms.bpp.dao.UsersRepo;
import com.ms.bpp.dto.MessageResponse;
import com.ms.bpp.entity.*;
import com.ms.bpp.services.auth.UserDetailsImpl;
import com.ms.bpp.common.model.common.Provider;
import com.ms.bpp.common.model.common.TagGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddDataService {
    private static final Logger logger = LoggerFactory.getLogger(AddDataService.class);
    @Autowired
    ProvidersRepo providersRepo;

    @Autowired
    UsersRepo usersRepo;

    public MessageResponse addMentorDetailsData(List<Provider> provider) {
        try {
            logger.info("Request {} {}", SecurityContextHolder.getContext().getAuthentication().getName(), provider);
            UserDetailsImpl user = getCurrentUserDetails();
            List<Providers> saveProviders = new ArrayList<>();
            provider.forEach(request -> {
                Providers providers = new Providers();
                if (!ObjectUtils.isEmpty(request.getDescriptor())) {
                    providers.setCode(request.getDescriptor().getCode());
                    providers.setName(request.getDescriptor().getName());
                    providers.setShortDesc(request.getDescriptor().getShortDesc());
                    providers.setLongDesc(request.getDescriptor().getLongDesc());
                }
                providers.setCategories(getCategories(request, providers));
                providers.setFulfillments(getFulfillments(request, providers, user));
                setItems(providers, request);
                providers.setMentorId(user.getId());
                providers.setMentorName(user.getFullname());
                saveProviders.add(providers);
            });
            providersRepo.saveAll(saveProviders);
        } catch (Exception e) {
            logger.error("Exception ", e);
            return new MessageResponse("Failed to add Session details, Please try again");
        }
        return new MessageResponse("Session details are added");
    }

    private UserDetailsImpl getCurrentUserDetails() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private void setItems(Providers providers, Provider request) {
        logger.info("setItems start");
        List<Items> itemsList = new ArrayList<>();
        request.getItems().forEach(item -> {
            Items items = new Items();
            Quantity quantity = new Quantity();
            quantity.setAllocated(item.getQuantity().getAllocated().getCount());
            quantity.setAvailable(item.getQuantity().getAvailable().getCount());
            items.setQuantity(quantity);
            quantity.setItems(items);
            items.setPrice(item.getPrice().getValue());
            items.setCategories(Collections.singletonList(providers.getCategories().stream().map(Categories::getCategoryId).collect(Collectors.joining(","))));
            items.setCode(item.getDescriptor().getCode());
            items.setName(item.getDescriptor().getName());
            items.setShortDesc(item.getDescriptor().getShortDesc());
            items.setLongDesc(item.getDescriptor().getLongDesc());
            items.setFulfillments(Collections.singletonList(providers.getFulfillments().stream().map(Fulfillments::getId).collect(Collectors.joining(","))));
            items.setTags(getTags(item.getTags(), providers));
            items.setProviders(providers);
            itemsList.add(items);
        });
        providers.setItems(itemsList);
        logger.info("setItems end");
    }

    private List<Fulfillments> getFulfillments(Provider request, Providers providers, UserDetailsImpl users) {
        logger.info("getFulfillments start");
        List<Fulfillments> fulfillmentsList = new ArrayList<>();
        request.getFulfillments().forEach(fulfillment -> {
            Fulfillments fulfillments = new Fulfillments();
            String generator = UUID.randomUUID().toString();
            fulfillments.setId(generator);
            fulfillments.setLanguage(fulfillment.getLanguage());
            fulfillments.setStartTime(fulfillment.getTime().getRange().getStart());
            fulfillments.setStartEnd(fulfillment.getTime().getRange().getEnd());
            fulfillments.setLabel(fulfillment.getTime().getLabel());
            fulfillments.setType(fulfillment.getType());
            fulfillments.setTags(getTags(fulfillment.getTags(), providers));
            fulfillments.setAgentPersonName(users.getFullname());
            fulfillments.setAgentPersonId(users.getId());
            fulfillments.setProviders(providers);
            fulfillmentsList.add(fulfillments);
        });
        logger.info("getFulfillments end");
        return fulfillmentsList;
    }

    private List<Tags> getTags(List<TagGroup> tagsRequest, Providers providers) {
        logger.info("getTags start");
        List<Tags> saveTags = new ArrayList<>();
        tagsRequest.forEach(tagGroup -> {
            Tags tags = new Tags();
            tags.setDisplay(tagGroup.isDisplay());
            tags.setCode(tagGroup.getDescriptor().getCode());
            tags.setName(tagGroup.getDescriptor().getName());
            List<ListTagsDescriptor> listTagsDescriptors = new ArrayList<>();
            tagGroup.getList().forEach(list -> {
                ListTagsDescriptor listTagsDescriptor = new ListTagsDescriptor();
                listTagsDescriptor.setCode(list.getDescriptor().getCode());
                listTagsDescriptor.setName(list.getDescriptor().getName());
                listTagsDescriptor.setTags(tags);
                listTagsDescriptors.add(listTagsDescriptor);
            });
            tags.setListTagsDescriptor(listTagsDescriptors);
            saveTags.add(tags);
        });
        logger.info("getTags end");
        return saveTags;
    }


    private static List<Categories> getCategories(Provider request, Providers providers) {
        logger.info("getCategories start");
        List<Categories> listCategories = new ArrayList<>();
        request.getCategories().forEach(category -> {
            Categories categories = new Categories();
            categories.setCategoryId(category.getId());
            categories.setCode(category.getDescriptor().getCode());
            categories.setName(category.getDescriptor().getName());
            categories.setProviders(providers);
            listCategories.add(categories);
        });
        logger.info("getCategories end");
        return listCategories;
    }
}
*/
