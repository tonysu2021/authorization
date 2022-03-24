package com.commerce.auth.unit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.auth.server.AuthServerApplication;
import com.auth.server.app.service.PermissionService;
import com.auth.server.web.dto.response.PermissionResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { AuthServerApplication.class })
@WebAppConfiguration
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
class PermissionControllerTests {
	private MockMvc mvc;

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	public void setup() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@MockBean
	private PermissionService permissionService;

	@Test
	@Order(1)
	void testFindPerTreeByPermissionId() throws Exception {
		PermissionResponse fakeResp = new PermissionResponse();
		fakeResp.setParentId("per0");
		fakeResp.setId("per37");
		fakeResp.setName("系統管理");
		fakeResp.setEnname("System");
		fakeResp.setUrl("/");
		Mockito.when(permissionService.findByPermissionId("per37")).thenReturn(fakeResp);

		mvc.perform(get("/permission/per37")).andExpect(status().isOk()).andExpect(content().string(containsString("per37")));
	}
}
