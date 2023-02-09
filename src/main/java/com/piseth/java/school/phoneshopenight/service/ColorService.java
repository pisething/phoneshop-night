package com.piseth.java.school.phoneshopenight.service;

import com.piseth.java.school.phoneshopenight.entity.Color;

public interface ColorService {
	Color create(Color color);
	Color getById(Long id);
}
