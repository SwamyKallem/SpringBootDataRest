package com.hibernatedirty.customdirty;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultFlushEntityEventListener;
import org.hibernate.event.spi.FlushEntityEvent;
import org.springframework.stereotype.Component;

import com.hibernatedirty.annotations.IgnoreDirtyProperty;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class CustomDefaultFlushEntityEventListener extends DefaultFlushEntityEventListener{
	
	@Override
	protected void dirtyCheck(final FlushEntityEvent event) throws HibernateException {
		super.dirtyCheck(event);
		excludeIgnoreDirtyPropertiesFromDirtyCheck(event);
	}

	private void excludeIgnoreDirtyPropertiesFromDirtyCheck(FlushEntityEvent event) {
		String[] propertyNames = event.getEntityEntry().getPersister().getPropertyNames();
		int[] dirtyProperties = event.getDirtyProperties();
		Object[] propertyValues = event.getPropertyValues();
		
		if(dirtyProperties == null) {
			return;
		}
		
		List<String> ignoreDirtyPropertiesList = FieldUtils.getFieldsListWithAnnotation(event.getEntity().getClass(), IgnoreDirtyProperty.class)
		.stream().map(Field::getName)
		.distinct().collect(Collectors.toList());
		
		List<Integer> newDirtyPropertiesList = new ArrayList<>();		
		
		for(int dirtPropertyIdex :  dirtyProperties) {
			
			if(!ignoreDirtyPropertiesList.contains(propertyNames[dirtPropertyIdex])  && !(propertyValues[dirtPropertyIdex] instanceof List)) {
				newDirtyPropertiesList.add(dirtPropertyIdex);
			}
			
		}
		
		int[] newDirtyPropertyArray = newDirtyPropertiesList.stream().mapToInt( index -> index).toArray();
		event.setDirtyProperties(newDirtyPropertyArray);
		
	}
}
