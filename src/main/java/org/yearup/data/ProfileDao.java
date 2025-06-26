package org.yearup.data;


import org.yearup.models.Profile;

import javax.validation.Valid;

public interface ProfileDao
{
    Profile create(Profile profile);

    Profile getByUserId(int id);

    Profile update(int id, @Valid Profile profile);
}
