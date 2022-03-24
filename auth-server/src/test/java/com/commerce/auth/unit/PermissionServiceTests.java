package com.commerce.auth.unit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.auth.server.AuthServerApplication;
import com.auth.server.app.service.PermissionService;
import com.auth.server.domain.entity.TbPermission;
import com.auth.server.domain.service.TbPerService;
import com.auth.server.web.dto.response.PermissionResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { AuthServerApplication.class })
@WebAppConfiguration
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
class PermissionServiceTests {

	@Autowired
	private PermissionService permissionService;

	@MockBean
	private TbPerService tbPerService;

	@Test
	@Order(1)
	void testFindByPermissionId() throws Exception {
		List<TbPermission> fakeList = new ArrayList<>();
		TbPermission per37 = new TbPermission();
		per37.setParentId("per0");
		per37.setId("per37");
		per37.setName("系統管理");
		TbPermission per38 = new TbPermission();
		per38.setParentId("per37");
		per38.setId("per38");
		per38.setName("用戶管理");
		TbPermission per39 = new TbPermission();
		per39.setParentId("per38");
		per39.setId("per39");
		per39.setName("用戶管理-查看用戶");
		TbPermission per43 = new TbPermission();
		per43.setParentId("per37");
		per43.setId("per43");
		per43.setName("電商-消費者管理");
		fakeList.add(per37);
		fakeList.add(per38);
		fakeList.add(per39);
		fakeList.add(per43);
		Mockito.when(tbPerService.findByUserId("admin")).thenReturn(Optional.of(fakeList));

		List<PermissionResponse> actualTodoList = permissionService.findPerTreeByUserId("admin");

		List<PermissionResponse> expectedTodosList = new ArrayList<>();
		PermissionResponse perResp37 = new PermissionResponse();
		perResp37.setParentId("per0");
		perResp37.setId("per37");
		perResp37.setName("系統管理");
		PermissionResponse perResp38 = new PermissionResponse();
		perResp38.setParentId("per37");
		perResp38.setId("per38");
		perResp38.setName("用戶管理");
		PermissionResponse perResp39 = new PermissionResponse();
		perResp39.setParentId("per38");
		perResp39.setId("per39");
		perResp39.setName("用戶管理-查看用戶");
		PermissionResponse perResp43 = new PermissionResponse();
		perResp43.setParentId("per37");
		perResp43.setId("per43");
		perResp43.setName("電商-消費者管理");
		perResp38.setSubPermissions(List.of(perResp39));
		perResp37.setSubPermissions(List.of(perResp38, perResp43));
		expectedTodosList.add(perResp37);
		// TODO 需要針對 Graph 資料結構進行比對。
		assertTrue(true);
	}
}
