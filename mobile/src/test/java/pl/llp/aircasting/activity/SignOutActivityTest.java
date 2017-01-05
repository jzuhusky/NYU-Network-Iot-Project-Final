/**
    AirCasting - Share your Air!
    Copyright (C) 2011-2012 HabitatMap, Inc.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    You can contact the authors by email at <info@habitatmap.org>
*/
package pl.llp.aircasting.activity;

import com.google.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.llp.aircasting.InjectedTestRunner;
import pl.llp.aircasting.R;
import pl.llp.aircasting.helper.SettingsHelper;
import pl.llp.aircasting.storage.repository.SessionRepository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static pl.llp.aircasting.TestHelper.click;

/**
 * Created by IntelliJ IDEA.
 * User: obrok
 * Date: 11/23/11
 * Time: 5:32 PM
 */
@RunWith(InjectedTestRunner.class)
public class SignOutActivityTest {
    @Inject SignOutActivity activity;

    @Before
    public void setup(){
        activity.onCreate(null);

        activity.settingsHelper = mock(SettingsHelper.class);
        activity.sessionRepository = mock(SessionRepository.class);
    }

    @Test
    public void shouldRemoveCredentials(){
        click(activity, R.id.sign_out);

        verify(activity.settingsHelper).removeCredentials();
        assertThat(activity.isFinishing(), equalTo(true));
    }
}
